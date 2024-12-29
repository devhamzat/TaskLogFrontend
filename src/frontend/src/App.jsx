import CreateAccount from "./pages/CreateAccount";
import Landing from "./pages/Landing";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import AccountActivation from "./pages/AccountActivation";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route exact path="/" element={<Landing />} />
          {/* <Route exact path="/createAccount" element={<CreateAccount />} /> */}
          <Route exact path="/register" element={<Register/>}/>
          <Route exact path="/sign_in" element={<Login />} />
          <Route exact path="/activate-account" element= {<AccountActivation />}/>
        </Routes>
      </Router>
    </>
  );
}

export default App;
