import { createRoot } from "react-dom/client";
import "./index.css";
import { Routes } from "@generouted/react-router";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import "@/lib/axios.config";
import { SidebarProvider, SidebarTrigger } from "./components/ui/sidebar";
import MySidebar from "./components/MySidebar";

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
      retry: 0,
    },
  },
});

createRoot(document.getElementById("root")!).render(
  <QueryClientProvider client={queryClient}>
    <SidebarProvider>
      <MySidebar />
      <SidebarTrigger />
      <Routes />
    </SidebarProvider>
  </QueryClientProvider>
);
