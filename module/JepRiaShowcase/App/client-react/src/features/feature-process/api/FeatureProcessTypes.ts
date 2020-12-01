export interface FeatureProcess {
  featureId: number;
  featureProcessId: number;
  featureStatusCode: string;
  featureStatusName: string;
  dateIns: Date;
}

export interface FeatureProcessCreate {
  featureStatusCode: string;
}

export interface FeatureStatusOptions {
  name: string;
  value: string;
}
