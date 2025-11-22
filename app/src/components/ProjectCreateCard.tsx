import { Plus } from "lucide-react";

import { Controller } from "react-hook-form";
import { useProjectCreateForm, type ProjectCreateType } from "@/forms/project";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
import { Label } from "./ui/label";
import { DialogClose } from "@radix-ui/react-dialog";
import { ProjectAPI } from "@/api/ProjectAPI";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { Spinner } from "./ui/spinner";

interface ProjectCreateCardProps {
  title?: string;
}

function create(data: ProjectCreateType) {
  return ProjectAPI.create(data.title);
}

export default function ProjectCreateCard(props: ProjectCreateCardProps) {
  const client = useQueryClient();
  const mutation = useMutation({
    mutationKey: ["projetos"],
    mutationFn: (data: ProjectCreateType) => create(data),
    onSuccess: () => {
      client.refetchQueries({
        queryKey: ["projetos"],
      });
    },
  });

  const currentForm = useProjectCreateForm();
  return (
    <Dialog>
      <DialogTrigger asChild>
        <div
          className="
    group cursor-pointer min-w-60 h-40 rounded-2xl bg-muted/40 
    border border-border 
    flex flex-col items-center justify-center gap-3
    transition-all duration-300 
    hover:bg-muted hover:shadow-lg hover:border-primary/40
    active:scale-[0.98]
  "
        >
          <div
            className="
      flex items-center justify-center 
      w-14 h-14 rounded-xl bg-primary/20 
      transition-colors duration-300 
      group-hover:bg-primary/30
    "
          >
            <Plus className="text-primary" size={36} />
          </div>

          {props.title && (
            <span className="text-sm font-medium text-muted-foreground group-hover:text-primary transition-colors">
              {props.title}
            </span>
          )}
        </div>
      </DialogTrigger>
      <DialogContent aria-describedby="Criar novo projeto">
        <DialogHeader>
          <DialogTitle>Criar novo projeto</DialogTitle>
        </DialogHeader>

        <form
          onSubmit={currentForm.handleSubmit((data) => mutation.mutate(data))}
        >
          <Controller
            control={currentForm.control}
            name="title"
            render={({ field }) => (
              <div className="flex flex-col w-full justify-between gap-4">
                <Label
                  htmlFor="title"
                  className="text-sm font-medium text-foreground dark:text-foreground"
                  title="Nome do projeto"
                >
                  Nome do projeto
                </Label>
                <Input
                  {...field}
                  type="text"
                  name="title"
                  id="title"
                  placeholder="Ex: Meus documentos velhos"
                  autoComplete="on"
                />
              </div>
            )}
          />
        </form>
        <DialogFooter>
          <DialogClose asChild>
            <Button variant="destructive">Cancelar</Button>
          </DialogClose>
          <Button
            onClick={() =>
              currentForm.handleSubmit((data) => mutation.mutate(data))
            }
            type="submit"
            variant="outline"
          >
            {currentForm.formState.isLoading || mutation.isPending ? (
              <Spinner />
            ) : (
              "Criar"
            )}
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
