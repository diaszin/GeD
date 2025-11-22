import { Edit, OrigamiIcon } from "lucide-react";
import { Button } from "./ui/button";
import DeleteButtonWithAlert from "./DeleteButtonWithAlert";
import { ProjectAPI } from "@/api/ProjectAPI";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import {
  Dialog,
  DialogHeader,
  DialogTitle,
  DialogContent,
  DialogTrigger,
  DialogFooter,
  DialogClose,
} from "./ui/dialog";
import { useProjectUpdateForm } from "@/forms/project";
import { Controller } from "react-hook-form";
import { Input } from "./ui/input";
import { Link } from "@/router";

interface ProjectCardProps {
  title: string;
  id: string;
}

interface ProjectEditButtonProps {
  id: string;
  title?: string;
}

function deleteProject(id: string) {
  return ProjectAPI.deleteById(id);
}

function updateProject(id: string, title: string) {
  return ProjectAPI.updateTitle(id, title);
}

function ProjectEditButton(props: ProjectEditButtonProps) {
  const queryClient = useQueryClient();
  const form = useProjectUpdateForm({
    title: props.title || "",
  });

  const projectID = props.id;

  const mutation = useMutation({
    mutationKey: ["update-project", "projeto"],
    mutationFn: ({ id, title }: { id: string; title: string }) =>
      updateProject(id, title),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["projetos"],
      });
    },
  });

  return (
    <Dialog>
      <DialogTrigger>
        <Button asChild variant="outline">
          <Edit className="w-14 text-primary" />
        </Button>
      </DialogTrigger>
      <DialogContent>
        <form
          className="flex flex-col gap-4"
          onSubmit={form.handleSubmit((data) =>
            mutation.mutate({ id: projectID, title: data.title })
          )}
        >
          <DialogHeader>
            <DialogTitle>Atualizar titulo do projeto</DialogTitle>
          </DialogHeader>

          <Controller
            control={form.control}
            name="title"
            render={({ field }) => (
              <div>
                <div className="flex flex-col w-full justify-between gap-4">
                  <Input
                    {...field}
                    type="text"
                    name="title"
                    id="title"
                    defaultValue={props.title}
                    autoComplete="on"
                  />
                </div>
              </div>
            )}
          />
          <DialogFooter>
            <DialogClose asChild>
              <Button className="bg-destructive">Cancelar</Button>
            </DialogClose>
            <Button disabled={props.title == form.watch("title")} type="submit">
              Atualizar
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}

export default function ProjectCard(props: ProjectCardProps) {
  const queryClient = useQueryClient();
  const deleteFetch = useMutation({
    mutationKey: ["delete-project", "projeto"],
    mutationFn: (id: string) => deleteProject(id),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["projetos"],
      });
    },
  });

  return (
    <div
      className="
    group w-full h-40 rounded-xl bg-card border border-border
    p-4 flex flex-col justify-between
    transition-all duration-300
    hover:shadow-md hover:border-primary/30
  "
    >
      <Link to="/projects/:id" params={{ id: props.id }}>
        <div className="flex items-center gap-4">
          <div
            className="
          min-w-14 h-14 rounded-xl bg-primary/10 flex items-center justify-center
          transition-colors duration-300 group-hover:bg-primary/20
        "
          >
            <OrigamiIcon size={32} className="text-primary opacity-80" />
          </div>

          <span
            className="
          text-xl font-semibold text-foreground
          group-hover:text-primary transition-colors
          overflow-hidden text-ellipsis
        "
          >
            {props.title || "Projeto sem nome"}
          </span>
        </div>
      </Link>

      <div className="flex items-center justify-end gap-2">
        <DeleteButtonWithAlert
          message="Ao excluir um projeto, ele será removido permanentemente!"
          alertTitle="Tem certeza disso?"
          confirmAction={() => deleteFetch.mutate(props.id)}
        />

        <ProjectEditButton id={props.id} title={props.title} />
      </div>
    </div>
  );
}
