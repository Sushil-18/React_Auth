import { redirect } from "react-router-dom";
import store from "../store/Store";
import { logout } from "../store/authSlice";

export function LogOut() {
  localStorage.removeItem("token");
  store.dispatch(logout());
  return redirect("/");
}
