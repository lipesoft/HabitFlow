require("dotenv").config();
const express = require("express");
const cors = require("cors");
const connectDB = require("./src/config/database");
const habitRoutes = require("./src/routes/habitRoutes");
const authRoutes = require("./src/routes/authRoutes");

const app = express();

connectDB();

app.use(cors());
app.use(express.json());

app.use("/habits", habitRoutes);
app.use("/auth", authRoutes);

app.get("/", (req, res) => res.json({ status: "ok" }));

const PORT = process.env.PORT || 3333;

app.listen(PORT, "0.0.0.0", () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});