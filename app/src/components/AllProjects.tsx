import type { Project } from "@/types/Project";
import ProjectCard from "./ProjectCard";

interface AllProjectsProps {
  data: Project[];
}

export default function AllProjects(props: AllProjectsProps) {
  return props.data.map((project, ind) => (
    <ProjectCard key={ind} title={project.title} id={project.id} />
  ));
}
