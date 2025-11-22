import MySidebar from "@/components/MySidebar";
import { SidebarTrigger } from "@/components/ui/sidebar";
import { Outlet } from "react-router";

export default function AllFoldersPages() {
  return (
    <>
      <MySidebar />
      <SidebarTrigger />
      <Outlet />
    </>
  );
}
