import { useParams } from "@/router";
import React from "react";

export default function FolderViewPage() {
  const { id } = useParams("/folder/:id");
  return <div>{id}</div>;
}
