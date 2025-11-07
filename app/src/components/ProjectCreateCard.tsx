import { Plus } from "lucide-react";

import { Controller } from "react-hook-form";
import { useProjectCreateForm, type ProjectCreateType } from "@/forms/project";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
import { Label } from "./ui/label";
import { DialogClose } from "@radix-ui/react-dialog";
import { Project } from "@/api/Project";

interface ProjectCreateCardProps {
  title?: string;
}

function create(data: ProjectCreateType) {
  console.log(data.title);
  return Project.create(data.title);
}

export default function ProjectCreateCard(props: ProjectCreateCardProps) {
  const currentForm = useProjectCreateForm();
  return (
    <Dialog>
      <DialogTrigger asChild>
        <div className="cursor-pointer w-2xs h-36 bg-primary shadow-2xs pl-2 py-2 rounded-md  transition-shadow flex justify-center items-center flex-col hover:shadow">
          <Plus color="white" size={50} />
          {props.title && <span>{props.title}</span>}
        </div>
      </DialogTrigger>
      <DialogContent aria-describedby="Criar novo projeto">
        <DialogHeader>
          <DialogTitle>Criar novo projeto</DialogTitle>
        </DialogHeader>

        <form onSubmit={currentForm.handleSubmit(create)}>
          <Controller
            control={currentForm.control}
            name="title"
            render={({ field }) => (
              <div className="flex flex-col w-full justify-between gap-4">
                <Label
                  htmlFor="title"
                  className="text-sm font-medium text-foreground dark:text-foreground"
                  title="Nome do projeto"
                >
                  Nome do projeto
                </Label>
                <Input
                  {...field}
                  type="text"
                  name="title"
                  id="title"
                  placeholder="Ex: Meus documentos velhos"
                  autoComplete="on"
                />
              </div>
            )}
          />
        </form>
        <DialogFooter>
          <DialogClose asChild>
            <Button variant="destructive">Cancelar</Button>
          </DialogClose>
          <Button type="submit" variant="outline">
            Criar
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
