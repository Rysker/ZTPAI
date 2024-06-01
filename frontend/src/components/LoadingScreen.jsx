import { motion } from "framer-motion";
import React from "react";
import { FaFighterJet } from "react-icons/fa";

const LoadingContainer = {
    width: "10rem",
    height: "20rem",
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-around",
    alignItems: "center",
};

const ContainerVariants = {
    initial: {
        transition: {
            staggerChildren: 0.2
        }
    },
    animate: {
        transition: {
            staggerChildren: 0.2
        }
    }
};

const topLeftVariant = {
    initial: { x: "0%", y: "0%" },
    animate: { x: "-100%", y: "-100%" }
};

const rightVariant = {
    initial: { x: "0%", y: "0%" },
    animate: { x: "100%", y: "0%" }
};

const bottomRightVariant = {
    initial: { x: "0%", y: "0%" },
    animate: { x: "-100%", y: "100%" }
};

const JetTransition = {
    duration: 1,
    yoyo: Infinity,
    ease: "easeInOut"
};

const iconStyle = {
    fontSize: "4.5rem",
    color: "white"
};

function LoadingScreen() {
    return (
        <div
            style={{
                width: "100%",
                height: "100vh",
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                background: "#0091D0"
            }}
        >
            <motion.div
                style={LoadingContainer}
                variants={ContainerVariants}
                initial="initial"
                animate="animate"
            >
                <motion.span
                    style={iconStyle}
                    variants={topLeftVariant}
                    transition={JetTransition}
                >
                    <FaFighterJet />
                </motion.span>
                <motion.span
                    style={iconStyle}
                    variants={rightVariant}
                    transition={JetTransition}
                >
                    <FaFighterJet />
                </motion.span>
                <motion.span
                    style={iconStyle}
                    variants={bottomRightVariant}
                    transition={JetTransition}
                >
                    <FaFighterJet />
                </motion.span>
            </motion.div>
        </div>
    );
}

export default LoadingScreen;