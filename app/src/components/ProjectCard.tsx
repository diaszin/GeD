import { Edit, FolderIcon } from "lucide-react";
import { Button } from "./ui/button";
import DeleteButtonWithAlert from "./DeleteButtonWithAlert";
import { ProjectAPI } from "@/api/ProjectAPI";
import { useMutation, useQueryClient } from "@tanstack/react-query";

interface ProjectCardProps {
  title: string;
  id: string;
}

function deleteProject(id: string) {
  return ProjectAPI.deleteById(id);
}

export default function ProjectCard(props: ProjectCardProps) {
  const queryClient = useQueryClient();
  const deleteFetch = useMutation({
    mutationKey: ["delete-project", "projeto"],
    mutationFn: (id: string) => deleteProject(id),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["projetos"]
      })
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
        <Button asChild variant="outline">
          <Edit className="w-14 text-primary" />
        </Button>
      </div>
    </div>
  );
}
