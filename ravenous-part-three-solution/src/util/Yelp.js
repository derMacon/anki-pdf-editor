const apiKey = 9HKRzxwyRS6hNaa15BPUOKSUNnAARqJ6AJCa0QJdusbzomqGIvlErZxnFnkNJTpi0OoO-Kt20ntBaYXrhC9_lJbNCY-UOAPEV-xZ9EUQA3zWMa0CoQ5EfSnIsXjfW3Yx
const yelp = {
  serchYelp(term, location, sortBy) {
    return fetch(`https://api.yelp.com/v3/businesses/search?term=${term}&location=${location}&sort_by=${sortBy}`);
  }
};
