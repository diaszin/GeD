import { Edit, FolderIcon } from "lucide-react";
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
    <div className="w-full h-36 bg-secondary shadow-2xs pl-2 py-2 rounded-md  transition-shadow flex justify-between flex-col hover:shadow">
      <div className="flex items-center gap-3 text-2xl font-medium text-gray-400">
        <FolderIcon color="blue" opacity={0.08} size={50} />
        {props.title || "Projeto sem nome"}
      </div>

      <div className="flex items-center justify-end">
        <DeleteButtonWithAlert
          message="Ao excluir um projeto, ele será removido permanentemente !"
          alertTitle="Tem certeza disso?"
          confirmAction={() => {
            deleteFetch.mutate(props.id);
          }}
        />
        <ProjectEditButton id={props.id} title={props.title} />
      </div>
    </div>
  );
}
