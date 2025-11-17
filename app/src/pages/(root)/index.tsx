import { ProjectAPI } from "@/api/ProjectAPI";
import AllProjects from "@/components/AllProjects";
import ProjectCreateCard from "@/components/ProjectCreateCard";
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

  if (!projectsFetch.data) {
    return null;
  }

  return (
    <div className="w-full h-full">
      <div
        className="
    grid gap-4 
    grid-cols-1 
    sm:grid-cols-2 
    lg:grid-cols-3 
    xl:grid-cols-4
  "
      >
        <ProjectCreateCard />

        <AllProjects data={projectsFetch.data} />
      </div>
    </div>
  );
}
