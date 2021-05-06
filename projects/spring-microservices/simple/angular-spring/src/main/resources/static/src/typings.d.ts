declare var proc: Process

interface Process {
    env: Env
}

interface Env {
    ENABLEDEBUGTOOLS: boolean,
    LOGLEVEL: string 
}

interface GlobalEnvironment {
    proc: Process
}