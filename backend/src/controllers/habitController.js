const Habit = require("../models/Habit");

exports.createHabit = async (req, res) => {
  try {
    const { title, frequency } = req.body;

    const habit = new Habit({
      title,
      frequency,
      user: req.user.id
    });

    await habit.save();

    res.status(201).json(habit);

  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getHabits = async (req, res) => {
  try {
    const habits = await Habit.find({
      user: req.user.id
    });

    res.json(habits);

  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.updateHabit = async (req, res) => {
  try {
    const { id } = req.params;

    const habit = await Habit.findOneAndUpdate(
      { _id: id, user: req.user.id },
      req.body,
      { new: true }
    );

    res.json(habit);

  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.deleteHabit = async (req, res) => {
  try {
    const { id } = req.params;

    await Habit.findOneAndDelete({
      _id: id,
      user: req.user.id
    });

    res.json({ message: "Hábito removido" });

  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};