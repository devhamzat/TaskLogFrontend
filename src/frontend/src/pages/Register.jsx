import React from "react";
import axios from "axios";
import { useFormik } from "formik";
import * as Yup from "yup";
import { NotebookTabs } from "lucide-react";
import { Link, useNavigate } from "react-router-dom";
export default function Register() {
  const baseUrl = process.env.REACT_APP_BACKEND_BASE_URL;
  const navigate = useNavigate();
  const formik = useFormik({
    initialValues: {
      username: "",
      email: "",
      password: "",
      confirm_password: "",
      terms_and_condition: false,
    },
    validationSchema: Yup.object({
      username: Yup.string()
        .min(4, "Username must be at least 4 characters long")
        .required("Username is required"),
      email: Yup.string()
        .email("Invalid email address")
        .required("Email is required"),
      password: Yup.string()
        .min(8, "Password must be at least 8 characters long")
        .matches(/[A-Z]/, "Password must contain at least one uppercase letter")
        .matches(/[a-z]/, "Password must contain at least one lowercase letter")
        .matches(/\d/, "Password must contain at least one number")
        .matches(
          /[\W_]/,
          "Password must contain at least one special character"
        )
        .required("Password is required"),
      confirm_password: Yup.string()
        .oneOf([Yup.ref("password"), null], "Passwords must match")
        .required("Confirm Password is required"),
        terms_and_condition: Yup.boolean()
        .oneOf([true], "You must accept the Terms and Conditions")
        .required("You must accept the Terms and Conditions"),
    }),
    onSubmit: async (values) => {
      try {
        console.log(values);
        const registerUrl = `${baseUrl}/auth/register`;
        const response = await axios.post(registerUrl, values);
        console.log(response);
        navigate("/");
      } catch (error) {
        console.log(error.response);
      }
    },
  });

  return (
    <section className="bg-white-50 dark:bg-white-100">
      <div className="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
        <Link
          className="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white"
          to="/"
        >
          <NotebookTabs />
          <span className="text-xl tracking-tight">TaskLogue</span>
        </Link>
        <div className="w-full rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:border-gray-700 bg-transparent backdrop-blur">
          <div className="p-6 space-y-4 md:space-y-6 sm:p-8">
            <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white text-center">
              Create an account
            </h1>
            <form
              className="space-y-4 md:space-y-6"
              onSubmit={formik.handleSubmit}
            >
              <div>
                <label
                  htmlFor="username"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  username
                </label>
                <input
                  type="text"
                  name="username"
                  id="username"
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="JohnDoe"
                  {...formik.getFieldProps("username")}
                />
                {formik.touched.username && formik.errors.username ? (
                  <span className="text-red-500 text-sm">
                    {formik.errors.username}
                  </span>
                ) : null}
              </div>
              <div>
                <label
                  htmlFor="email"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Email
                </label>
                <input
                  type="email"
                  name="email"
                  id="email"
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="name@company.com"
                  {...formik.getFieldProps("email")}
                />
                {formik.touched.email && formik.errors.email ? (
                  <span className="text-red-500 text-sm">
                    {formik.errors.email}
                  </span>
                ) : null}
              </div>
              <div>
                <label
                  htmlFor="password"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Password
                </label>
                <input
                  type="password"
                  name="password"
                  id="password"
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="••••••••"
                  {...formik.getFieldProps("password")}
                />
                {formik.touched.password && formik.errors.password ? (
                  <span className="text-red-500 text-sm">
                    {formik.errors.password}
                  </span>
                ) : null}
              </div>
              <div>
                <label
                  htmlFor="confirm_password"
                  className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  Confirm Password
                </label>
                <input
                  type="password"
                  name="confirm_password"
                  id="confirm_password"
                  className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  placeholder="••••••••"
                  {...formik.getFieldProps("confirm_password")}
                />
                {formik.touched.confirm_password &&
                formik.errors.confirm_password ? (
                  <span className="text-red-500 text-sm">
                    {formik.errors.confirm_password}
                  </span>
                ) : null}
              </div>
              <div>
                <input
                  type="checkbox"
                  id="terms_and_conditionn"
                  name="terms_and_condition"
                  {...formik.getFieldProps("terms_and_condition")}
                />
                <label
                  htmlFor="termsAndCondition"
                  className="ml-2 text-sm font-medium text-gray-900 dark:text-white"
                >
                  I accept the{" "}
                  <Link to="/terms" className="text-blue-500 underline">
                    Terms and Conditions
                  </Link>
                </label>
                {formik.touched.terms_and_condition && formik.errors.terms_and_condition ? (
                  <span className="text-red-500 text-sm">{formik.errors.terms_and_condition}</span>
                ) : null}
              </div>
              <button
                type="submit"
                className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
              >
                Create an account
              </button>
              <p className="text-sm font-light text-gray-500 dark:text-gray-400">
                 Already have an account?{" "}
                <Link 
                   to="/sign_in"
                   className="font-medium text-primary-600 hover:underline dark:text-primary-500"
                 >
                   Login here
                 </Link>
               </p>
            </form>
          </div>
        </div>
      </div>
    </section>
  );
}
