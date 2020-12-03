export const API_PATH: string = `${
  process.env.NODE_ENV === "development"
    ? process.env.REACT_APP_API_URL
    : "/JepRiaShowcase/api"
}`;
