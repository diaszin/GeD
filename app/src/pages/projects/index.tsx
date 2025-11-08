import { ProjectAPI } from "@/api/ProjectAPI";
import AllProjects from "@/components/AllProjects";
import ProjectCreateCard from "@/components/ProjectCreateCard";
import { useQuery } from "@tanstack/react-query";

function getProjects() {
  return ProjectAPI.get();
}

export default function ProjectHomePage() {
  const projectsFetch = useQuery({
    queryKey: ["projetos"],
    queryFn: () => getProjects().then((response) => response.data),
    retry: 1
  });

  if(projectsFetch.isError){
    return <span>Deu erro !</span>
  }

  if(!projectsFetch.data){
    return <></>
  }

  return (
    <div className="w-screen h-screen">
      <div className=" grid grid-cols-3 gap-3">
        <ProjectCreateCard />

        <AllProjects data={projectsFetch.data}/>
      </div>
    </div>
  );
}
