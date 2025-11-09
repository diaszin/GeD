export interface Project {
    id: string
    title: string
    createdAt: Date
    owner: {
        email: string
        name: string
    }
}