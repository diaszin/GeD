import { useFolderCreateForm } from "@/forms/folder";
import { Controller } from "react-hook-form";
import { Input } from "./ui/input";
import {
  DialogClose,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "./ui/dialog";
import { Button } from "./ui/button";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { FolderAPI } from "@/api/FolderAPI";

interface FolderCreateFormProps {
  projectID: string;
}

function create(title: string, projectID: string) {
  return FolderAPI.create(title, projectID);
}

export default function FolderCreateForm(props: FolderCreateFormProps) {
  const query = useQueryClient();
  const form = useFolderCreateForm();
  const mutation = useMutation({
    mutationKey: ["pastas"],
    mutationFn: ({ title }: { title: string }) =>
      create(title, props.projectID),
    onSuccess: () => {
      query.refetchQueries({
        queryKey: ["pastas"],
      });
    },
  });

  return (
    <DialogContent>
      <DialogHeader>
        <DialogTitle>Criar nova pasta</DialogTitle>
        <DialogDescription>
          Organize seu projeto. Crie um novo projeto
        </DialogDescription>
      </DialogHeader>
      <form
        onSubmit={form.handleSubmit(({ title }) => mutation.mutate({ title }))}
      >
        <Controller
          control={form.control}
          name="title"
          render={({ field }) => (
            <Input
              {...field}
              type="text"
              name="title"
              id="title"
              placeholder="Documentos antes do periodo judicial"
              autoComplete="on"
            />
          )}
        />
        <legend className="text-sm text-gray-600">
          Pressione Enter para criar uma nova pasta
        </legend>
      </form>
      <DialogFooter>
        <DialogClose>
          <Button variant="destructive">Cancelar</Button>
        </DialogClose>
      </DialogFooter>
    </DialogContent>
  );
}
