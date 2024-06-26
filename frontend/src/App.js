import LoginPage from "./pages/LoginPage";
import Vehicles from "./pages/Vehicles";
import VehicleDetails from "./pages/VehicleDetails";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import "./App.css"
import ModelKit from "./pages/ModelKit";
import RegisterPage from "./pages/RegisterPage";
import MyCollection from "./pages/MyCollection";
import Profile from "./pages/Profile";
import MainPage from "./pages/MainPage";
import HomePage from "./pages/HomePage";
import AdminPage from "./pages/AdminPage";
import Reports from "./pages/Reports";
import PrivateRoute from "./components/PrivateRoute";
import axios from "axios";
import FollowersPage from "./pages/FollowersPage";

axios.defaults.withCredentials = true;

function App ()
{
    return (
        <BrowserRouter>
            <Routes>
                <Route path ='/' element = { <MainPage/>  } />
                <Route path ='/login' element = { <LoginPage/> } />
                <Route path ='/register' element = { <RegisterPage/> } />
                <Route path ='/home' element = {<PrivateRoute> <HomePage/> </PrivateRoute>} />
                <Route path ='/vehicles' element = {<PrivateRoute> <Vehicles/> </PrivateRoute>} />
                <Route path ='/vehicles/:vehicle_name' element={<PrivateRoute> <VehicleDetails /> </PrivateRoute>} />
                <Route path ='/vehicles/:vehicle_name/:id' element={<PrivateRoute> <ModelKit/> </PrivateRoute>} />
                <Route path ='/profile/:profileName/collection' element={<PrivateRoute> <MyCollection/> </PrivateRoute>} />
                <Route path ='/profile/:profileName' element={<PrivateRoute> <Profile/> </PrivateRoute>} />
                <Route path ='/admin' element={<PrivateRoute roles={['ADMIN']}> <AdminPage/> </PrivateRoute>} />
                <Route path ='/admin/reports' element={<PrivateRoute roles={['ADMIN']}> <Reports/> </PrivateRoute>} />
                <Route path ='/followers' element={<PrivateRoute> <FollowersPage/> </PrivateRoute>} />
            </Routes>
        </BrowserRouter>
    )
}

export default App;