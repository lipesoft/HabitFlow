require("dotenv").config({ path: __dirname + "/.env" });
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

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});