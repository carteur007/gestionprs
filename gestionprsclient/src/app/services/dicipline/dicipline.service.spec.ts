import { TestBed } from '@angular/core/testing';

import { DiciplineService } from './dicipline.service';

describe('DiciplineService', () => {
  let service: DiciplineService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DiciplineService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
