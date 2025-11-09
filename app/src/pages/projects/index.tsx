import { ProjectAPI } from "@/api/ProjectAPI";
import AllProjects from "@/components/AllProjects";
import ProjectCreateCard from "@/components/ProjectCreateCard";
import {
  AlertDialog,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";
import { useNavigate } from "@/router";
import type { Project } from "@/types/Project";
import { useQuery } from "@tanstack/react-query";
import type { AxiosError } from "axios";

function getProjects() {
  return ProjectAPI.get();
}

export default function ProjectHomePage() {
  const projectsFetch = useQuery<Project[], AxiosError>({
    queryKey: ["projetos"],
    queryFn: () => getProjects().then((response) => response.data),
    retry: 1,
  });

  const navigate = useNavigate();

  if (projectsFetch.isError) {
    if (projectsFetch.error.status == 403) {
      return (
        <AlertDialog open>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Sua sessão expirou</AlertDialogTitle>
            </AlertDialogHeader>
            <AlertDialogDescription>
              Opa! Sua sessão expirou. Faça login novamente para continuar
              usando o sistema
            </AlertDialogDescription>
            <AlertDialogFooter>
              <Button
                onClick={() => {
                  navigate({
                    pathname: "/signin",
                  });
                }}
                className="w-full"
              >
                Realizar login novamente
              </Button>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
      );
    }

    return (
      <AlertDialog open>
        <AlertDialogContent>
          <AlertDialogHeader>
            <AlertDialogTitle>
              Erro desconhecido. Tente novamente mais tarde !
            </AlertDialogTitle>
          </AlertDialogHeader>
          <AlertDialogDescription>
            Ocorreu um erro inesperado. Nossa equipe já foi notificada e estamos
            trabalhando para resolver o problema.
          </AlertDialogDescription>
          <AlertDialogFooter>
            <Button className="w-full">Realizar login novamente</Button>
          </AlertDialogFooter>
        </AlertDialogContent>
      </AlertDialog>
    );
  }

  if (!projectsFetch.data) {
    return null;
  }

  return (
    <div className="w-screen h-screen">
      <div className=" grid grid-cols-3 gap-3">
        <ProjectCreateCard />

        <AllProjects data={projectsFetch.data} />
      </div>
    </div>
  );
}
