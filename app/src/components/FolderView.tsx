import {
  ContextMenu,
  ContextMenuContent,
  ContextMenuItem,
  ContextMenuTrigger,
} from "@/components/ui/context-menu";
import { Dialog, DialogTrigger } from "@/components/ui/dialog";
import { FolderPlusIcon } from "lucide-react";
import type { ReactElement } from "react";
import FolderCreateForm from "./FolderCreateForm";

interface FolderViewProps {
  projectID: string;
  children?: ReactElement | ReactElement[] | null;
}

export default function FolderView(props: FolderViewProps) {
  return (
    <div>
      <Dialog>
        <ContextMenu>
          <ContextMenuTrigger className="w-full h-screen flex items-center justify-center">
            {props.children != null ? (
              props.children
            ) : (
              <legend className="text-lg text-gray-600">
                Clique com o botão direito do mouse para criar uma pasta
              </legend>
            )}
          </ContextMenuTrigger>
          <ContextMenuContent className="w-32">
            <DialogTrigger asChild>
              <ContextMenuItem>
                <span className="flex items-center justify-center gap-1.5">
                  <FolderPlusIcon />
                  Nova pasta
                </span>
              </ContextMenuItem>
            </DialogTrigger>
          </ContextMenuContent>
        </ContextMenu>
        <FolderCreateForm projectID={props.projectID} />
      </Dialog>
    </div>
  );
}
