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
import { FolderOpen, HomeIcon, LogOut } from "lucide-react";
import { useQuery } from "@tanstack/react-query";
import { ProjectAPI } from "@/api/ProjectAPI";
import { useNavigate } from "@/router";
import { AuthToken } from "@/config/AuthToken";

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
                    <a
                      className="text-[#747bff] font-medium decoration-inherit"
                      href={`/projects/${projects.id}`}
                    >
                      {projects.title}
                    </a>
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
  const navigate = useNavigate();
  return (
    <Sidebar collapsible="offcanvas">
      <SidebarContent>
        <SidebarGroup className="group-data-[collapsible=icon]:hidden">
          <SidebarGroupLabel>Geral</SidebarGroupLabel>
          <SidebarGroupContent>
            <SidebarMenu>
              <SidebarMenuItem>
                <SidebarMenuButton asChild>
                  <a href="/">
                    <HomeIcon />
                    Home
                  </a>
                </SidebarMenuButton>
              </SidebarMenuItem>
              <SidebarMenuItem className="cursor-pointer">
                <SidebarMenuButton
                  onClick={() => {
                    AuthToken.remove();
                    navigate("/signin");
                  }}
                >
                  <LogOut />
                  Logout
                </SidebarMenuButton>
              </SidebarMenuItem>
              <MyProjects />
            </SidebarMenu>
          </SidebarGroupContent>
        </SidebarGroup>
      </SidebarContent>
    </Sidebar>
  );
}
