import { File } from "lucide-react";
import { Card, CardContent, CardHeader, CardTitle } from "./ui/card";

interface FileCardProps {
  id: string;
  title: string;
  uploadDate?: string | null;
  createdBy: string;
}

export default function FileCard(props: FileCardProps) {
  return (
    <Card className="w-full max-w-sm p-4">
      <CardHeader className="flex flex-row items-center gap-3 pb-2">
        <File className="h-6 w-6" />
        <CardTitle className="text-lg">{props.title}</CardTitle>
      </CardHeader>

      <CardContent className="text-sm text-muted-foreground">
        {props.uploadDate && <span>Feito upload em: {props.uploadDate}</span>}
        Subido por: {props.createdBy}
      </CardContent>
    </Card>
  );
}
