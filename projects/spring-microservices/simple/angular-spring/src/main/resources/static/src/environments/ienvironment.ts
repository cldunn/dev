export type LogLevel = 'debug' | 'info' | 'warn' | 'error';

export interface IEnvironment {
  enableDebugTools: boolean;  // Enables use of ng.profiler.timeChangeDetection(); in browser console
  production: boolean;
  logLevel: string;
}