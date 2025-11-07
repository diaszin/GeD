import { FolderIcon } from "lucide-react";


interface ProjectCardProps {
  title: string
}


export default function ProjectCard(props: ProjectCardProps) {
  return (
    <div className="w-2xs h-36 bg-secondary shadow-2xs pl-2 py-2 rounded-md  transition-shadow flex justify-between flex-col hover:shadow">
      
      <span className="text-2xl font-medium text-gray-400">{props.title || "Projeto sem nome"}</span>
      
      
        <FolderIcon color="blue" opacity={0.08} size={80}/>
      
    </div>
  );
}
