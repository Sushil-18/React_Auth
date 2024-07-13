import { json, redirect } from "react-router-dom";
import AuthForm from "../components/AuthForm";
import store from "../store/Store";
import { login } from "../store/authSlice";

function AuthenticationPage() {
  return <AuthForm />;
}

export default AuthenticationPage;

export async function action({ request }) {
  const searchParamas = new URL(request.url).searchParams;
  const mode = searchParamas.get("mode") || "login";
  const data = await request.formData();
  const authData = {
    email: data.get("email"),
    password: data.get("password"),
  };

  const response = await fetch("http://localhost:8080/" + mode, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(authData),
  });

  if (!response.ok) {
    return json({ message: "could not authnticate user." }, { status: 500 });
  }

  if (response.status === 422 || response.status === 401) {
    return response;
  }

  const resData = await response.json();

  const token = resData.token;
  //Storing JWT token in localstorage as backend accepts it from only localhost
  localStorage.setItem("token", token);
  const expiration = new Date();
  expiration.setHours(expiration.getHours() + 1);
  localStorage.setItem("expiration", expiration.toISOString());
  store.dispatch(login());

  return redirect("/");
}
