import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import z from "zod";

export type ProjectCreateType = z.infer<typeof projectCreateFormSchema>;

export const projectCreateFormSchema = z.object({
  title: z.string().nonempty(),
});

export function useProjectCreateForm() {
  const form = useForm({
    resolver: zodResolver(projectCreateFormSchema),
    defaultValues: {
      title: "",
    },
  });

  return form;
}
