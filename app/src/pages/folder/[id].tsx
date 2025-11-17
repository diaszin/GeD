import { FolderAPI } from "@/api/FolderAPI";
import FileCard from "@/components/FileCard";
import FileView from "@/components/FileView";
import { useParams } from "@/router";
import { useQuery } from "@tanstack/react-query";

function getFiles(id: string) {
  return FolderAPI.getFiles(id);
}

interface AllFilesProps {
  id: string;
}

function AllFiles(props: AllFilesProps) {
  const folderFilesFetch = useQuery({
    queryKey: ["arquivos"],
    queryFn: () => getFiles(props.id).then((response) => response.data),
    retry: 1,
  });

  if (!folderFilesFetch.data) {
    return null;
  }

  if (folderFilesFetch.data.length == 0) {
    return null;
  }

  return (
    <div className="grid grid-cols-3 gap-2 gap-y-5">
      {folderFilesFetch.data.map((file) => (
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

  return (
    <div>
      <FileView folder={id}>
        <AllFiles id={id} />
      </FileView>
    </div>
  );
}
