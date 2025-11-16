import { FolderAPI } from "@/api/FolderAPI";
import FileView from "@/components/FileView";
import { useParams } from "@/router";
import { useQuery } from "@tanstack/react-query";

function getFiles(id: string) {
  return FolderAPI.getFiles(id);
}

export default function FolderViewPage() {
  const { id } = useParams("/folder/:id");
  const folderFilesFetch = useQuery({
    queryKey: ["arquivos"],
    queryFn: () => getFiles(id),
    retry: 1,
  });

  return (
    <div>
      <FileView folder={id} />
    </div>
  );
}
