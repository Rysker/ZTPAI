import {FaFlag} from "react-icons/fa";
import '../App.css';
import '../styles/AdminPage.css';
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Webpage from "../components/Webpage";

function AdminPage()
{
    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar></DefaultNavbar>
                    <div className="content-space-support">
                        <div className="support-pick">
                            <a href="/admin/reports">
                                <FaFlag></FaFlag>
                            </a>
                            <p>Verify reports</p>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}
export default AdminPage;