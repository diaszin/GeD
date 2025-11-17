import { FolderAPI } from "@/api/FolderAPI";
import FileCard from "@/components/FileCard";
import FileView from "@/components/FileView";
import { useParams } from "@/router";
import { useQuery } from "@tanstack/react-query";
import type { File as TFile } from "@/types/File";

function getFiles(id: string) {
  return FolderAPI.getFiles(id);
}

interface AllFilesProps {
  data: TFile[];
}

function AllFiles(props: AllFilesProps) {
  if (!props.data) {
    return null;
  }

  if (props.data.length == 0) {
    return null;
  }

  return (
    <div className="grid grid-cols-3 gap-2 gap-y-5">
      {props.data.map((file) => (
        <FileCard
          id={file.id}
          title={
            file.extension ? file.title + "." + file.extension : file.title
          }
          uploadDate={file.uploadDate}
          createdBy={file.createdBy.email}
        />
      ))}
    </div>
  );
}

export default function FolderViewPage() {
  const { id } = useParams("/folder/:id");

  const folderFilesFetch = useQuery({
    queryKey: ["arquivos"],
    queryFn: () => getFiles(id).then((response) => response.data),
    retry: 1,
  });

  return (
    <div>
      <FileView folder={id}>
        {folderFilesFetch.data && folderFilesFetch.data.length > 0 ? (
          <AllFiles data={folderFilesFetch.data} />
        ) : null}
      </FileView>
    </div>
  );
}
