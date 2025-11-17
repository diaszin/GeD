import {
  DialogClose,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "./ui/dialog";
import { useFileUploadForm, type FileUploadFormType } from "@/forms/file";
import { Controller } from "react-hook-form";
import { Input } from "./ui/input";
import { Label } from "./ui/label";
import { Button } from "./ui/button";
import { FileAPI } from "@/api/FileAPI";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { FormsSubmitError } from "./FormsSubmitError";
import type { AxiosError, AxiosResponse } from "axios";

function upload(data: FileUploadFormType, folder: string) {
  const file: File = data.file[0]; // Onde fica o arquivo

  return FileAPI.upload(data.title, file, folder);
}

interface FileUploadFormProps {
  folder: string;
}

export default function FileUploadForm(props: FileUploadFormProps) {
  const queryClient = useQueryClient();
  const form = useFileUploadForm();
  const mutation = useMutation<
    AxiosResponse,
    AxiosError<{
      message: string | string[];
    }>,
    {
      data: FileUploadFormType;
      folder: string;
    },
    unknown
  >({
    mutationKey: ["arquivos"],
    mutationFn: ({
      data,
      folder,
    }: {
      data: FileUploadFormType;
      folder: string;
    }) => upload(data, folder),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["arquivos"],
      });
    },
  });

  return (
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Importar novo arquivo</DialogTitle>
      </DialogHeader>
      <form
        onSubmit={form.handleSubmit((data) =>
          mutation.mutate({
            data: data,
            folder: props.folder,
          })
        )}
        className="flex flex-col gap-4 "
      >
        <FormsSubmitError isError={mutation.isError} error={mutation.error} />
        <Controller
          name="file"
          control={form.control}
          render={({ field }) => (
            <Input
              type="file"
              name="file"
              id="file"
              onChange={(data) => {
                field.onChange(data.currentTarget.files);
              }}
            />
          )}
        />

        <Controller
          name="title"
          control={form.control}
          render={({ field }) => (
            <div className="flex flex-col gap-1.5">
              <Label
                htmlFor="title"
                className="text-sm font-medium text-foreground dark:text-foreground"
                title="Nome do projeto"
              >
                Nome do projeto
              </Label>
              <Input {...field} type="text" name="title" id="title" />
            </div>
          )}
        />
      </form>
      <DialogFooter>
        <DialogClose>
          <Button variant={"secondary"}>Cancelar</Button>
        </DialogClose>
        <Button
          onClick={form.handleSubmit((data) =>
            mutation.mutate({
              data: data,
              folder: props.folder,
            })
          )}
          disabled={!form.formState.isValid}
        >
          Importar
        </Button>
      </DialogFooter>
    </DialogContent>
  );
}
