import { redirect } from "react-router-dom";

export function LogOut() {
  localStorage.removeItem("token");
  return redirect("/");
}
