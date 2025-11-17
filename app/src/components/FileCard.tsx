import { Edit, File } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";
import { Button } from "./ui/button";
import DeleteButtonWithAlert from "./DeleteButtonWithAlert";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { FileAPI } from "@/api/FileAPI";
import { useFileUpdateForm, type FileUpdateFormType } from "@/forms/file";
import { useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
import { Controller } from "react-hook-form";
import { Label } from "./ui/label";
import { Input } from "./ui/input";

interface FileCardProps {
  id: string;
  title: string;
  uploadDate?: string | null;
  createdBy: string;
}

interface EditFileProps {
  id: string;
}

function removeFile(id: string) {
  return FileAPI.delete(id);
}

function update(id: string, data: FileUpdateFormType) {
  return FileAPI.update(id, data);
}

function EditFile(props: EditFileProps) {
  const [dialogIsOpen, setDialogIsOpen] = useState<boolean>();
  const queryClient = useQueryClient();
  const form = useFileUpdateForm();
  const { mutate } = useMutation({
    mutationKey: ["arquivos", "update-arquivos"],
    mutationFn: (data: FileUpdateFormType) => update(props.id, data),
    onSuccess: () => {
      setDialogIsOpen(false);
      queryClient.refetchQueries({
        queryKey: ["arquivos"],
      });
    },
  });

  return (
    <Dialog open={dialogIsOpen} onOpenChange={setDialogIsOpen}>
      <DialogTrigger>
        <Button asChild variant="ghost">
          <Edit className="w-14 text-primary" />
        </Button>
      </DialogTrigger>
      <DialogContent>
        <form
          className="flex flex-col gap-4"
          onSubmit={form.handleSubmit((data) => mutate(data))}
        >
          <DialogHeader>
            <DialogTitle>Alterar informações da pasta</DialogTitle>
            <DialogDescription>
              Altere as informações já inseridas da sua pasta'
            </DialogDescription>
          </DialogHeader>

          <Controller
            control={form.control}
            name="title"
            render={({ field }) => (
              <div className="flex flex-col w-full justify-between gap-4">
                <Label
                  htmlFor="title"
                  className="text-sm font-medium text-foreground dark:text-foreground"
                  title="Nome do arquivo"
                >
                  Nome do arquivo
                </Label>
                <Input
                  {...field}
                  type="text"
                  name="title"
                  id="title"
                  autoComplete="on"
                />
              </div>
            )}
          />
        </form>
        <DialogFooter>
          <Button
            type="submit"
            onClick={form.handleSubmit((data) => mutate(data))}
          >
            Alterar
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

export default function FileCard(props: FileCardProps) {
  const queryClient = useQueryClient();
  const mutation = useMutation({
    mutationKey: ["arquivos"],
    mutationFn: (id: string) => removeFile(id),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["arquivos"],
      });
    },
  });

  return (
    <Card className="w-lg max-w-sm p-4">
      <CardHeader className="flex flex-row items-center gap-3 pb-2">
        <File className="h-6 w-6" />
        <CardTitle className="text-lg w-full">
          <div className="flex w-full justify-between items-center">
            {props.title}
            <div className="flex items-center justify-between gap-2">
              <EditFile id={props.id} />

              <DeleteButtonWithAlert
                message="Ao excluir esse arquivo ele será excluido permanentemente da nossa base"
                alertTitle={"Deseja excluir esse arquivo?"}
                confirmAction={() => mutation.mutate(props.id)}
              />
            </div>
          </div>
        </CardTitle>
      </CardHeader>

      <CardContent className="text-sm text-muted-foreground flex flex-col gap-2">
        Subido por: {props.createdBy}
        {props.uploadDate && <span>Feito upload em: {props.uploadDate}</span>}
      </CardContent>
    </Card>
  );
}
