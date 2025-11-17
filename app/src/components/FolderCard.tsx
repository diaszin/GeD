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
import { Edit, FolderIcon } from "lucide-react";
import { Controller } from "react-hook-form";
import { Label } from "./ui/label";
import { Input } from "./ui/input";
import { useState } from "react";
import { Link } from "@/router";

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
                  title="Nome da pasta"
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
    <div
      className="
    group w-72 h-40 rounded-xl bg-card border border-border
    p-4 flex flex-col justify-between
    transition-all duration-300
    hover:shadow-md hover:border-primary/30
  "
    >
      <Link
        to="/folder/:id"
        params={{ id: props.id }}
        className="flex items-center gap-3"
      >
        <div
          className="
        w-12 h-12 rounded-lg bg-primary/10
        flex items-center justify-center
        transition-colors duration-300
        group-hover:bg-primary/20
      "
        >
          <FolderIcon className="text-primary opacity-80" size={26} />
        </div>

        <span
          className="
        text-lg font-semibold text-foreground truncate
        group-hover:text-primary transition-colors
      "
        >
          {props.title || "Pasta sem nome"}
        </span>
      </Link>

      <div className="flex gap-2 justify-end">
        <DeleteButtonWithAlert
          message="Os arquivos serão excluídos permanentemente."
          alertTitle="Deseja realmente excluir essa pasta?"
          confirmAction={() => exclude()}
        />

        <FolderEditButton id={props.id} />
      </div>
    </div>
  );
}
