import { FolderAPI } from "@/api/FolderAPI";
import DeleteButtonWithAlert from "./DeleteButtonWithAlert";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { useFolderUpdateForm, type FolderUpdateFormType } from "@/forms/folder";
import {
  Dialog,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
  DialogContent,
  DialogDescription,
  DialogFooter,
} from "@/components/ui/dialog";
import { Button } from "./ui/button";
import { Edit } from "lucide-react";
import { Controller } from "react-hook-form";
import { Label } from "./ui/label";
import { Input } from "./ui/input";
import { useState } from "react";

interface FolderCardProps {
  title: string;
  id: string;
}

interface FolderEditButtonProps {
  id: string;
}

function deleteFolder(id: string) {
  return FolderAPI.delete(id);
}

function update(id: string, data: FolderUpdateFormType) {
  return FolderAPI.update(id, data);
}

function FolderEditButton(props: FolderEditButtonProps) {
  const [dialogIsOpen, setDialogIsOpen] = useState<boolean>();
  const queryClient = useQueryClient();
  const form = useFolderUpdateForm();
  const { mutate } = useMutation({
    mutationKey: ["pastas", "update-pastas"],
    mutationFn: (data: FolderUpdateFormType) => update(props.id, data),
    onSuccess: () => {
      setDialogIsOpen(false);
      queryClient.refetchQueries({
        queryKey: ["pastas"],
      });
    },
  });

  return (
    <Dialog open={dialogIsOpen} onOpenChange={setDialogIsOpen}>
      <DialogTrigger>
        <Button asChild>
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
                  title="Nome do projeto"
                >
                  Nome da pasta
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
          <Button type="submit" onClick={form.handleSubmit((data) => mutate(data))}>
            Alterar
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

export default function FolderCard(props: FolderCardProps) {
  const queryClient = useQueryClient();
  const { mutate: exclude } = useMutation({
    mutationKey: ["pastas"],
    mutationFn: () => deleteFolder(props.id),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["pastas"],
      });
    },
  });

  return (
    <div className="w-82 h-40 bg-gray-200 p-3 rounded-sm flex flex-col justify-between items-start">
      <div className="w-full flex items-center justify-between">
        <span className="text-xl">{props.title}</span>
      </div>

      <div className="flex gap-2">
        <DeleteButtonWithAlert
          message={"Os arquivos serão excluidos permanentemente"}
          alertTitle={"Deseja realmente excluir esses arquivos?"}
          confirmAction={() => {
            exclude();
          }}
        />

        <FolderEditButton id={props.id} />
      </div>
    </div>
  );
}
