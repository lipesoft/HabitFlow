const express = require("express");
const router = express.Router();
const authMiddleware = require("../middlewares/authMiddleware");

const {
  createHabit,
  getHabits,
  updateHabit,
  deleteHabit
} = require("../controllers/habitController");

router.use(authMiddleware);

router.post("/", createHabit);
router.get("/", getHabits);
router.put("/:id", updateHabit);
router.delete("/:id", deleteHabit);

module.exports = router;