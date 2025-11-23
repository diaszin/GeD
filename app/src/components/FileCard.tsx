import { Download, Edit, File } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";
import { Button } from "./ui/button";
import DeleteButtonWithAlert from "./DeleteButtonWithAlert";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { FileAPI } from "@/api/FileAPI";
import { useFileUpdateForm, type FileUpdateFormType } from "@/forms/file";
import { useState } from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
import { Controller } from "react-hook-form";
import { Label } from "./ui/label";
import { Input } from "./ui/input";

interface FileCardProps {
  id: string;
  title: string;
  uploadDate?: string | null;
  createdBy: string;
}

interface EditFileProps {
  id: string;
}

interface DownloadButtonProps {
  id: string;
  title: string;
}

interface FilePreviewDialogProps {
  id: string;
  children?: React.ReactNode;
  fileName: string;
}

function removeFile(id: string) {
  return FileAPI.delete(id);
}

function update(id: string, data: FileUpdateFormType) {
  return FileAPI.update(id, data);
}

function download(id: string) {
  return FileAPI.download(id);
}

function view(id: string) {
  return FileAPI.preview(id);
}

function EditFile(props: EditFileProps) {
  const [dialogIsOpen, setDialogIsOpen] = useState<boolean>();
  const queryClient = useQueryClient();
  const form = useFileUpdateForm();
  const { mutate } = useMutation({
    mutationKey: ["arquivos", "update-arquivos"],
    mutationFn: (data: FileUpdateFormType) => update(props.id, data),
    onSuccess: () => {
      setDialogIsOpen(false);
      queryClient.refetchQueries({
        queryKey: ["arquivos"],
      });
    },
  });

  return (
    <Dialog open={dialogIsOpen} onOpenChange={setDialogIsOpen}>
      <DialogTrigger>
        <Button asChild variant="ghost">
          <Edit className="w-14 text-primary" />
        </Button>
      </DialogTrigger>
      <DialogContent>
        <form
          className="flex flex-col gap-4"
          onSubmit={form.handleSubmit((data) => mutate(data))}
        >
          <DialogHeader>
            <DialogTitle>Alterar informações da pasta</DialogTitle>
            <DialogDescription>
              Altere as informações já inseridas da sua pasta'
            </DialogDescription>
          </DialogHeader>

          <Controller
            control={form.control}
            name="title"
            render={({ field }) => (
              <div className="flex flex-col w-full justify-between gap-4">
                <Label
                  htmlFor="title"
                  className="text-sm font-medium text-foreground dark:text-foreground"
                  title="Nome do arquivo"
                >
                  Nome do arquivo
                </Label>
                <Input
                  {...field}
                  type="text"
                  name="title"
                  id="title"
                  autoComplete="on"
                />
              </div>
            )}
          />
        </form>
        <DialogFooter>
          <Button
            type="submit"
            onClick={form.handleSubmit((data) => mutate(data))}
          >
            Alterar
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

function DownloadButton(props: DownloadButtonProps) {
  return (
    <Button
      onClick={async () => {
        const file = await download(props.id);

        const blob = new Blob([file.data]);
        const url = window.URL.createObjectURL(blob);

        const a = document.createElement("a");
        a.href = url;
        a.download = props.title; // você pode pegar do header, se quiser
        a.click();

        URL.revokeObjectURL(url);
      }}
      asChild
      variant="ghost"
    >
      <Download className="w-14 text-primary" />
    </Button>
  );
}

export function FilePreview({
  id,
  children,
  fileName,
}: FilePreviewDialogProps) {
  const [open, setOpen] = useState(false);
  const [fileUrl, setFileUrl] = useState<string | null>(null);
  const [mimeType, setMimeType] = useState<string>();

  async function handlePreview() {
    const response = await view(id);

    // mime type
    const type = response.data.type;
    setMimeType(type);

    // cria URL temporária
    const blobUrl = URL.createObjectURL(response.data);
    setFileUrl(blobUrl);

    setOpen(true);
  }

  function closeDialog(openState: boolean) {
    if (!openState && fileUrl) {
      URL.revokeObjectURL(fileUrl);
    }
    setOpen(openState);
  }

  const isImage = mimeType?.startsWith("image/");
  const isPdf = mimeType === "application/pdf";
  const isText =
    mimeType?.startsWith("text/") || mimeType === "application/json";

  return (
    <Dialog open={open} onOpenChange={closeDialog}>
      <DialogTrigger asChild>
        <span onClick={handlePreview} className="cursor-pointer">
          {children ?? (
            <button className="bg-primary text-primary-foreground px-4 py-2 rounded">
              Ver Arquivo
            </button>
          )}
        </span>
      </DialogTrigger>
      <DialogContent className="w-[70vw] h-[70vh] p-0 flex flex-col">
        <DialogHeader className="p-4 border-b flex justify-between items-center">
          <DialogTitle>{fileName ?? "Preview do Arquivo"}</DialogTitle>
        </DialogHeader>

        <div className="w-full flex-1 overflow-hidden flex items-center justify-center bg-muted/30 p-2">
          {!fileUrl && <p>Nenhum arquivo carregado</p>}

          {fileUrl && isImage && (
            <iframe src={fileUrl} className="h-full w-full rounded" width="100%" height="100%" />
          )}

          {fileUrl && isPdf && (
            <iframe src={fileUrl} className="w-full h-full border-0 rounded" />
          )}

          {fileUrl && isText && (
            <iframe
              src={fileUrl}
              className="w-full h-full border-0 bg-white rounded p-2"
            />
          )}

          {fileUrl && !isImage && !isPdf && !isText && (
            <p className="text-muted-foreground text-center">
              ❌ Preview não suportado para este tipo de arquivo.
            </p>
          )}
        </div>
      </DialogContent>
    </Dialog>
  );
}

export default function FileCard(props: FileCardProps) {
  const queryClient = useQueryClient();
  const mutation = useMutation({
    mutationKey: ["arquivos"],
    mutationFn: (id: string) => removeFile(id),
    onSuccess: () => {
      queryClient.refetchQueries({
        queryKey: ["arquivos"],
      });
    },
  });

  return (
    <Card className="w-lg max-w-sm p-4">
      <CardHeader className="flex flex-row items-center gap-3 pb-2">
        <CardTitle className="text-lg w-full">
          <div className="flex flex-col w-full justify-between items-center gap-4">
            <div className="flex items-center justify-between min-w-28 gap-4">
              <FilePreview id={props.id} fileName={props.title}>
                <File className="h-6 w-6" />
                {props.title}
              </FilePreview>
            </div>
            <div className="flex items-center justify-between gap-2">
              <DownloadButton id={props.id} title={props.title} />
              <EditFile id={props.id} />

              <DeleteButtonWithAlert
                message="Ao excluir esse arquivo ele será excluido permanentemente da nossa base"
                alertTitle={"Deseja excluir esse arquivo?"}
                confirmAction={() => mutation.mutate(props.id)}
              />
            </div>
          </div>
        </CardTitle>
      </CardHeader>

      <CardContent className="text-sm text-muted-foreground flex flex-col gap-2">
        Subido por: {props.createdBy}
        {props.uploadDate && <span>Feito upload em: {props.uploadDate}</span>}
      </CardContent>
    </Card>
  );
}
