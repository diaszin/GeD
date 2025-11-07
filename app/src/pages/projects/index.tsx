import ProjectCreate from "@/components/ProjectCard";
import ProjectCreateCard from "@/components/ProjectCreateCard";

export default function ProjectHomePage() {
  return (
    <div className="w-screen h-screen">
      
      <div className="flex items-center gap-5">
        <ProjectCreateCard/>
        <ProjectCreate title="Teste de criação de projeto" />
      </div>
    </div>
  )
}
