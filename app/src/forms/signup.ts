import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import z from "zod";

export type SignUpFormType = z.infer<typeof signUpFormSchema>;

export const signUpFormSchema = z
  .object({
    name: z.string().nonempty(),
    email: z.email().nonempty(),
    password: z.string().min(8, {
      error: "A senha deve conter mais de 8 caracteres",
    }),
    confirmedPassword: z.string(),
    birthdayDate: z.iso
      .date()
      .nonempty()
      .refine(
        (date) => {
          const birtdayDate = new Date(date);

          // Define idade mínima para se cadastrar
          const allowedAge = 18;
          const minDate = new Date();

          // Define data máxima para se cadastrar
          const maxDate = new Date();
          const year = minDate.getFullYear();

          minDate.setFullYear(year - allowedAge);
          maxDate.setFullYear(maxDate.getFullYear() - 200);


          return maxDate <= birtdayDate && birtdayDate <= minDate;
        },
        {
          error: "Você precisar ser maior de 18 anos para se cadastrar ",
        }
      ),
  })
  .refine((data) => data.password == data.confirmedPassword, {
    error: "As senha não coincidem",
    path: ["confirmPassword"],
  });

export function useSignUpForm() {
  const form = useForm<SignUpFormType>({
    resolver: zodResolver(signUpFormSchema),
    defaultValues: {
      name: "",
      email: "",
      password: "",
      confirmedPassword: "",
    },
  });

  return form;
}
