const mongoose = require("mongoose");

const HabitSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true
  },
  frequency: {
    type: String,
    required: true
  },
  completed: {
    type: Boolean,
    default: false
  },
  user: {
    type: mongoose.Schema.Types.ObjectId,
    ref: "User",
    required: true
  }
}, { timestamps: true });

module.exports = mongoose.model("Habit", HabitSchema);