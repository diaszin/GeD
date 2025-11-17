import React from "react";
import {
  Sidebar,
  SidebarContent,
  SidebarGroup,
  SidebarGroupContent,
  SidebarGroupLabel,
  SidebarMenu,
  SidebarMenuButton,
  SidebarMenuItem,
  SidebarMenuSub,
  SidebarMenuSubItem,
} from "./ui/sidebar";
import {
  Collapsible,
  CollapsibleContent,
  CollapsibleTrigger,
} from "./ui/collapsible";
import { FolderOpen } from "lucide-react";
import { useQuery } from "@tanstack/react-query";
import { ProjectAPI } from "@/api/ProjectAPI";


function getProjects() {
  return ProjectAPI.get();
}

function MyProjects() {
  const projectsFetch = useQuery({
    queryKey: ["projetos"],
    queryFn: () => getProjects().then((response) => response.data),
    retry: 1,
  });

  return (
    <Collapsible defaultOpen className="group/collapsible">
      <SidebarMenuItem>
        <CollapsibleTrigger asChild>
          <SidebarMenuButton>
            <FolderOpen size={16} />
            Projetos
          </SidebarMenuButton>
        </CollapsibleTrigger>
        <CollapsibleContent>
          <SidebarMenuSub>
            <SidebarMenuSubItem className="flex flex-col gap-1.5">
              {projectsFetch.data &&
                projectsFetch.data.map((projects) => (
                  <SidebarMenuButton className="h-max">
                    <a href={`/projects/${projects.id}`}>{projects.title}</a>
                  </SidebarMenuButton>
                ))}
            </SidebarMenuSubItem>
          </SidebarMenuSub>
        </CollapsibleContent>
      </SidebarMenuItem>
    </Collapsible>
  );
}

export default function MySidebar() {
  return (
    <Sidebar collapsible="offcanvas">
      <SidebarContent>
        <SidebarGroup className="group-data-[collapsible=icon]:hidden">
          <SidebarGroupLabel>Geral</SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu>
              <MyProjects />
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  );
}
