import { Form, NavLink, useParams } from "react-router-dom";

import classes from "./MainNavigation.module.css";
import NewsletterSignup from "./NewsletterSignup";
import { useSelector } from "react-redux";

function MainNavigation() {
  const isLoggedin = useSelector((state) => state.auth.isLoggedin);
  /* const urlParams = new URLSearchParams(window.location.search);
  const mode = urlParams.get("mode"); */

  return (
    <header className={classes.header}>
      <nav>
        <ul className={classes.list}>
          <li>
            <NavLink
              to="/"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
              end
            >
              Home
            </NavLink>
          </li>
          <li>
            <NavLink
              to="/events"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              Events
            </NavLink>
          </li>

          <li>
            <NavLink
              to="/newsletter"
              className={({ isActive }) =>
                isActive ? classes.active : undefined
              }
            >
              Newsletter
            </NavLink>
          </li>
          {!isLoggedin && (
            <li>
              <NavLink
                to="/auth?mode=signup"
                className={({ isActive }) =>
                  isActive ? classes.active : undefined
                }
              >
                Sign Up
              </NavLink>
            </li>
          )}
          {isLoggedin && (
            <li>
              <Form action="/logout" method="post">
                <button>Log Out</button>
              </Form>
            </li>
          )}
        </ul>
      </nav>
      <NewsletterSignup />
    </header>
  );
}

export default MainNavigation;
