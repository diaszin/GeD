export interface File {
  id: string;
  title: string;
  extension: string;
  createdBy: {
    email: string;
  };
  uploadDate: string;
}
