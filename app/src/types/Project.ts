export interface Project {
    title: string
    createdAt: Date
    owner: {
        email: string
        name: string
    }
}