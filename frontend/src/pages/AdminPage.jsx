import {FaFlag} from "react-icons/fa";
import '../App.css';
import '../styles/AdminPage.css';
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";

function Profile()
{
    return (
        <div className="webpage">
            <DefaultNavbar></DefaultNavbar>
            <div className="content-space-support">
                <div className="support-pick">
                    <a href="/admin/reports">
                        <FaFlag></FaFlag>
                    </a>
                    <p>Verify reports</p>
                </div>
            </div>
        </div>
    )
}
export default Profile;