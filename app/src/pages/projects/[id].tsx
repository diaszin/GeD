import { ProjectAPI } from "@/api/ProjectAPI";
import FolderCard from "@/components/FolderCard";
import FolderView from "@/components/FolderView";
import { useParams } from "@/router";
import type { Folder } from "@/types/Folder";
import { useQuery } from "@tanstack/react-query";
import type { AxiosError } from "axios";

interface FolderGridProps {
  data: Folder[] | undefined;
}

function getFolders(projectID: string) {
  return ProjectAPI.allFolders(projectID);
}

function FolderGrid(props: FolderGridProps) {
  if (props.data && props.data.length > 0) {
    return (
      <div className="grid grid-cols-3 gap-3">
        {props.data.map((folder, ind) => (
          <FolderCard key={ind} title={folder.title} id={folder.id} />
        ))}
      </div>
    );
  }

  return null;
}

export default function ProjectViewPage() {
  const { id } = useParams("/projects/:id");
  const folderFetch = useQuery<Folder[], AxiosError>({
    queryKey: ["pastas"],
    queryFn: () => getFolders(id).then((response) => response.data),
    retry: 1,
  });

  return (
    <div className="w-full">
      <FolderView projectID={id}>
        <FolderGrid data={folderFetch.data} />
      </FolderView>
    </div>
  );
}
