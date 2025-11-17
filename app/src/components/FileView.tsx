import { type ReactElement } from "react";
import {
  ContextMenu,
  ContextMenuTrigger,
  ContextMenuContent,
  ContextMenuItem,
} from "./ui/context-menu";

import FileUploadForm from "./FileUploadForm";
import { Dialog, DialogTrigger } from "./ui/dialog";
import { FilePlus } from "lucide-react";

interface FileViewProps {
  folder: string;
  children?: ReactElement | null;
}

export default function FileView(props: FileViewProps) {
  return (
    <Dialog>
      <ContextMenu>
        <ContextMenuTrigger className="w-full flex items-center justify-center">
          {props.children || (
            <legend>
              Clique com o botão direito para importar um novo arquivo
            </legend>
          )}
        </ContextMenuTrigger>
        <ContextMenuContent className="w-max">
          <DialogTrigger asChild>
            <ContextMenuItem>
              <div className="flex items-center justify-center gap-1.5">
                <FilePlus />
                Importar novo arquivo
              </div>
            </ContextMenuItem>
          </DialogTrigger>
        </ContextMenuContent>
      </ContextMenu>
      <FileUploadForm folder={props.folder} />
    </Dialog>
  );
}
