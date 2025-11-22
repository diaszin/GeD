import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@radix-ui/react-label";
import { Controller } from "react-hook-form";
import { useSignUpForm, type SignUpFormType } from "@/forms/signup";
import { Auth } from "@/api/Auth";
import { useMutation } from "@tanstack/react-query";
import type { AxiosError } from "axios";
import { FormsSubmitError } from "@/components/FormsSubmitError";
import { useNavigate } from "@/router";
import { toast } from "sonner";
import { Spinner } from "@/components/ui/spinner";

function signUp(data: SignUpFormType) {
  return Auth.signUp(data.name, data.email, data.password, data.birthdayDate);
}

export default function SignupPage() {
  const form = useSignUpForm();
  const navigate = useNavigate();
  const mutation = useMutation<
    unknown,
    AxiosError<{
      message: string | string[];
    }>,
    SignUpFormType,
    unknown
  >({
    mutationKey: ["login"],
    mutationFn: (data: SignUpFormType) => signUp(data),
    onSuccess: () =>
      toast.success("Conta criada com sucesso", {
        duration: 2000,
        onAutoClose: () => {
          navigate("/signin");
        },
        position: "top-center"
      }),
  });

  return (
    <div className="w-screen h-screen flex items-center justify-center min-h-screen">
      <div className="flex flex-1 flex-col justify-center px-4 py-10 lg:px-6">
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
          <h3 className="mt-2 text-center text-lg font-bold text-foreground dark:text-foreground">
            Crie sua conta e coloque tudo em ordem hoje.
          </h3>
        </div>

        <Card className="mt-4 sm:mx-auto sm:w-full sm:max-w-md bg-gray-600 rounded-md bg-clip-padding backdrop-filter backdrop-blur-sm bg-opacity-20 border border-gray-100">
          <CardContent>
            <FormsSubmitError
              title="Os campos não foram inseridores corretamente"
              error={mutation.error}
              isError={mutation.isError}
            />

            <form
              onSubmit={form.handleSubmit((data) => mutation.mutate(data))}
              className="space-y-4"
            >
              <Controller
                control={form.control}
                name="name"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="name"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite seu nome completo
                    </Label>
                    <Input
                      {...field}
                      type="text"
                      id="name"
                      name="name"
                      autoComplete="name"
                      placeholder="Exemplo: Gestãozinho de Dó Cumentos"
                      className="mt-2"
                    />
                  </>
                )}
              />

              <Controller
                control={form.control}
                name="email"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="email"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite seu e-mail profissional
                    </Label>
                    <Input
                      {...field}
                      type="email"
                      id="email"
                      name="email"
                      autoComplete="email"
                      placeholder="Exemplo: gestaodedocumentos@ged.br"
                      className="mt-2"
                    />
                  </>
                )}
              />

              <Controller
                control={form.control}
                name="password"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="password"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite sua senha
                    </Label>
                    <Input
                      {...field}
                      type="password"
                      id="password"
                      name="password"
                      autoComplete="password"
                      className="mt-2"
                    />
                  </>
                )}
              />

              <Controller
                control={form.control}
                name="confirmedPassword"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="confirm-password"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite sua senha
                    </Label>
                    <Input
                      {...field}
                      type="password"
                      id="confirm-password"
                      name="confirm-password"
                      autoComplete="confirm-password"
                      className="mt-2"
                    />
                  </>
                )}
              />

              <Controller
                control={form.control}
                name="birthdayDate"
                render={({ field }) => (
                  <>
                    <Label
                      htmlFor="birthday"
                      className="text-sm font-medium text-foreground dark:text-foreground"
                    >
                      Digite a data de nascimento
                    </Label>
                    <Input
                      {...field}
                      type="date"
                      id="birthday"
                      name="birthday"
                      autoComplete="birthday"
                      className="mt-2"
                    />
                  </>
                )}
              />

              <Button
                disabled={!form.formState.isValid}
                type="submit"
                className="mt-4 w-full py-2 font-medium"
              >
                {form.formState.isSubmitting || mutation.isPending ? <Spinner /> : "Criar conta"}
              </Button>
            </form>
          </CardContent>
        </Card>

        <p className="mt-6 text-center text-sm text-muted-foreground dark:text-muted-foreground">
          Já tem uma conta?{" "}
          <a
            href="/signin"
            className="font-medium text-primary hover:text-primary/90 dark:text-primary hover:dark:text-primary/90"
          >
            Entre novamente
          </a>
        </p>
      </div>
    </div>
  );
}
