import MySidebar from "@/components/MySidebar";
import { SidebarTrigger } from "@/components/ui/sidebar";
import React from "react";
import { Outlet } from "react-router";

export default function AllProjectPages() {
  return (
    <>
      <MySidebar />
      <SidebarTrigger />
      <Outlet />
    </>
  );
}
